const Node = require('../Node');

let UnaryExpression = require('./UnaryExpression');

// ExponentiationExpression[Yield, Await] :
//    UnaryExpression[?Yield, ?Await]
//    UpdateExpression[?Yield, ?Await] ** ExponentiationExpression[?Yield, ?Await]
let ExponentiationExpression = (Yield, Await) => (given) => {
  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = UnaryExpression(Yield, Await)(given);
      return new Node('ExponentiationExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ ExponentiationExpression`);
  }
  Node.TODO('ExponentiationExpression');
}

module.exports = ExponentiationExpression;
