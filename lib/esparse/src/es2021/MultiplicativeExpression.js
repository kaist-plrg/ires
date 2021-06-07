const Node = require('../Node');

let ExponentiationExpression = require('./ExponentiationExpression');

// MultiplicativeExpression[Yield, Await] :
//    ExponentiationExpression[?Yield, ?Await]
//    MultiplicativeExpression[?Yield, ?Await] MultiplicativeOperator ExponentiationExpression[?Yield, ?Await]
let MultiplicativeExpression = (Yield, Await) => (given) => {
  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = ExponentiationExpression(Yield, Await)(given);
      return new Node('MultiplicativeExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ MultiplicativeExpression`);
  }
  Node.TODO('MultiplicativeExpression');
}

module.exports = MultiplicativeExpression;
