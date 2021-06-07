const Node = require('../Node');

let AdditiveExpression = require('./AdditiveExpression');

// ShiftExpression[Yield, Await] :
//    AdditiveExpression[?Yield, ?Await]
//    ShiftExpression[?Yield, ?Await] << AdditiveExpression[?Yield, ?Await]
//    ShiftExpression[?Yield, ?Await] >> AdditiveExpression[?Yield, ?Await]
//    ShiftExpression[?Yield, ?Await] >>> AdditiveExpression[?Yield, ?Await]
let ShiftExpression = (Yield, Await) => (given) => {
  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = AdditiveExpression(Yield, Await)(given);
      return new Node('ShiftExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ ShiftExpression`);
  }
  Node.TODO('ShiftExpression');
}

module.exports = ShiftExpression;
