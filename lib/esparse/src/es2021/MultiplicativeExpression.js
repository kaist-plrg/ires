const Node = require('../Node');

// MultiplicativeExpression[Yield, Await] :
//    ExponentiationExpression[?Yield, ?Await]
//    MultiplicativeExpression[?Yield, ?Await] MultiplicativeOperator ExponentiationExpression[?Yield, ?Await]
let MultiplicativeExpression = (Yield, Await) => (given) => {
  let ExponentiationExpression = require('./ExponentiationExpression');

  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'ObjectExpression':
    case 'ArrayExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = ExponentiationExpression(Yield, Await)(given);
      return new Node('MultiplicativeExpression', given, 0, [expr], params);
    }
    case 'BinaryExpression': {
      let { index, children } = Node.getBinary(
        given,
        ['*', '/', '%'],
        MultiplicativeExpression(Yield, Await),
        ExponentiationExpression(Yield, Await),
      );
      if (index > 0) {
        let [l, r] = children;
        let op = new Node('MultiplicativeOperator', {}, index - 1);
        children = [l, op, r];
        index = 1;
      }
      return new Node('AdditiveExpression', given, index, children, params);
    }
    default:
      Node.TODO(`${type} @ MultiplicativeExpression`);
  }
  Node.TODO('MultiplicativeExpression');
}

module.exports = MultiplicativeExpression;
