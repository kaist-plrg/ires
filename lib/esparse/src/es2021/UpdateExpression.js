const Node = require('../Node');

// UpdateExpression[Yield, Await] :
//    LeftHandSideExpression[?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] [no LineTerminator here] ++
//    LeftHandSideExpression[?Yield, ?Await] [no LineTerminator here] --
//    ++ UnaryExpression[?Yield, ?Await]
//    -- UnaryExpression[?Yield, ?Await]
let UpdateExpression = (Yield, Await) => (given) => {
  let LeftHandSideExpression = require('./LeftHandSideExpression');

  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'ObjectExpression':
    case 'ArrayExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = LeftHandSideExpression(Yield, Await)(given);
      return new Node('UpdateExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ UpdateExpression`);
  }
  Node.TODO('UpdateExpression');
}

module.exports = UpdateExpression;
