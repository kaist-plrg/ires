const Node = require('../Node');

let MultiplicativeExpression = require('./MultiplicativeExpression');

// AdditiveExpression[Yield, Await] :
//    MultiplicativeExpression[?Yield, ?Await]
//    AdditiveExpression[?Yield, ?Await] + MultiplicativeExpression[?Yield, ?Await]
//    AdditiveExpression[?Yield, ?Await] - MultiplicativeExpression[?Yield, ?Await]
let AdditiveExpression = (Yield, Await) => (given) => {
  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = MultiplicativeExpression(Yield, Await)(given);
      return new Node('AdditiveExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ AdditiveExpression`);
  }
  Node.TODO('AdditiveExpression');
}

module.exports = AdditiveExpression;
