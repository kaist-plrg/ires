const Node = require('../Node');

let NewExpression = require('./NewExpression');

// LeftHandSideExpression[Yield, Await] :
//    NewExpression[?Yield, ?Await]
//    CallExpression[?Yield, ?Await]
//    OptionalExpression[?Yield, ?Await]
let LeftHandSideExpression = (Yield, Await) => (given) => {
  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'Identifier':
    case 'Literal': {
      let expr = NewExpression(Yield, Await)(given);
      return new Node('LeftHandSideExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ LeftHandSideExpression`);
  }
  Node.TODO('LeftHandSideExpression');
}

module.exports = LeftHandSideExpression;
