const Node = require('../Node');

// NewExpression[Yield, Await] :
//    MemberExpression[?Yield, ?Await]
//    new NewExpression[?Yield, ?Await]
let NewExpression = (Yield, Await) => (given) => {
  let MemberExpression = require('./MemberExpression');

  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'ObjectExpression':
    case 'ArrayExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = MemberExpression(Yield, Await)(given);
      return new Node('NewExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ NewExpression`);
  }
  Node.TODO('NewExpression');
}

module.exports = NewExpression;
