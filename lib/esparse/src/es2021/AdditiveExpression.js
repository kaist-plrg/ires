const Node = require('../Node');

// AdditiveExpression[Yield, Await] :
//    MultiplicativeExpression[?Yield, ?Await]
//    AdditiveExpression[?Yield, ?Await] + MultiplicativeExpression[?Yield, ?Await]
//    AdditiveExpression[?Yield, ?Await] - MultiplicativeExpression[?Yield, ?Await]
let AdditiveExpression = (Yield, Await) => (given) => {
  let MultiplicativeExpression = require('./MultiplicativeExpression');

  let params = [Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'CallExpression':
    case 'MemberExpression':
    case 'ObjectExpression':
    case 'ArrayExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = MultiplicativeExpression(Yield, Await)(given);
      return new Node('AdditiveExpression', given, 0, [expr], params);
    }
    case 'BinaryExpression': {
      let { index, children } = Node.getBinary(
        given,
        ['+', '-'],
        AdditiveExpression(Yield, Await),
        MultiplicativeExpression(Yield, Await),
      );
      return new Node('AdditiveExpression', given, index, children, params);
    }
    default:
      Node.TODO(`${type} @ AdditiveExpression`);
  }
  Node.TODO('AdditiveExpression');
}

module.exports = AdditiveExpression;
