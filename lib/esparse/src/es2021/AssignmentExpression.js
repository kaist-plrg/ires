const Node = require('../Node');

// AssignmentExpression[In, Yield, Await] :
//    ConditionalExpression[?In, ?Yield, ?Await]
//    [+Yield]YieldExpression[?In, ?Await]
//    ArrowFunction[?In, ?Yield, ?Await]
//    AsyncArrowFunction[?In, ?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] = AssignmentExpression[?In, ?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] AssignmentOperator AssignmentExpression[?In, ?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] &&= AssignmentExpression[?In, ?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] ||= AssignmentExpression[?In, ?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] ??= AssignmentExpression[?In, ?Yield, ?Await]
let AssignmentExpression = (In, Yield, Await) => (given) => {
  const ConditionalExpression = require('./ConditionalExpression');
  const LeftHandSideExpression = require('./LeftHandSideExpression');
  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'BinaryExpression':
    case 'UpdateExpression':
    case 'CallExpression':
    case 'NewExpression':
    case 'MemberExpression':
    case 'ObjectExpression':
    case 'ArrayExpression':
    case 'FunctionExpression':
    case 'ClassExpression':
    case 'TemplateLiteral':
    case 'Identifier':
    case 'Literal': {
      let cond = ConditionalExpression(In, Yield, Await)(given);
      return new Node('AssignmentExpression', given, 0, [cond], params);
    }
    case 'AssignmentExpression': {
      let { left, operator, right } = given;
      let lhs = LeftHandSideExpression(Yield, Await)(left);
      let expr = AssignmentExpression(In, Yield, Await)(right);
      let node = new Node('AssignmentExpression', given, 4, [lhs, expr], params);
      switch (operator) {
        case '=': break;
        case '&&=': node.index = 6; break;
        case '||=': node.index = 7; break;
        case '??=': node.index = 8; break;
        default: {
          let ops = ['*=', '/=', '%=', '+=', '-=',
            '<<=', '>>=', '>>>=', '&=', '^=', '|=', '**='];
          let index = ops.indexOf(operator);
          let op = new Node('AssignmentOperator', {}, index);
          node.index = 5;
          node.children = [lhs, op, expr];
        }
      }
      return node;
    }
    default:
      Node.TODO(`${type} @ AssignmentExpression`);
  }
}

module.exports = AssignmentExpression;
