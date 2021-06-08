const Node = require('../Node');

// LeftHandSideExpression[Yield, Await] :
//    NewExpression[?Yield, ?Await]
//    CallExpression[?Yield, ?Await]
//    OptionalExpression[?Yield, ?Await]
let LeftHandSideExpression = (Yield, Await) => (given) => {
  const NewExpression = require('./NewExpression');
  const CallExpression = require('./CallExpression');
  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'NewExpression': {
      let expr = NewExpression(Yield, Await)(given);
      return new Node('LeftHandSideExpression', given, 0, [expr], params);
    }
    case 'MemberExpression': {
      let cur = given;
      while (cur?.type == 'MemberExpression') { cur = cur.object; }
      let expr;
      if (cur?.type == 'CallExpression') {
        expr = CallExpression(Yield, Await)(given);
      } else {
        expr = NewExpression(Yield, Await)(given);
      }
      return new Node('LeftHandSideExpression', given, 0, [expr], params);
    }
    case 'ObjectExpression':
    case 'ArrayExpression':
    case 'BinaryExpression':
    case 'FunctionExpression':
    case 'ClassExpression':
    case 'TemplateLiteral':
    case 'Identifier':
    case 'Literal': {
      let expr = NewExpression(Yield, Await)(given);
      return new Node('LeftHandSideExpression', given, 0, [expr], params);
    }
    case 'CallExpression': {
      let expr = CallExpression(Yield, Await)(given);
      return new Node('LeftHandSideExpression', given, 1, [expr], params);
    }
    default:
      Node.TODO(`${type} @ LeftHandSideExpression`);
  }
  Node.TODO('LeftHandSideExpression');
}

module.exports = LeftHandSideExpression;
