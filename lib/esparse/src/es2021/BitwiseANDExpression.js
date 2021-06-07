const Node = require('../Node');

let EqualityExpression = require('./EqualityExpression');

// BitwiseANDExpression[In, Yield, Await] :
//    EqualityExpression[?In, ?Yield, ?Await]
//    BitwiseANDExpression[?In, ?Yield, ?Await] & EqualityExpression[?In, ?Yield, ?Await]
let BitwiseANDExpression = (In, Yield, Await) => (given) => {
  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'ObjectExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = EqualityExpression(In, Yield, Await)(given);
      return new Node('BitwiseANDExpression', given, 0, [expr], params);
    }
    case 'BinaryExpression': {
      let { index, children } = Node.getBinary(given, ['&'],
        BitwiseANDExpression(In, Yield, Await),
        EqualityExpression(In, Yield, Await),
      );
      return new Node('BitwiseANDExpression', given, index, children, params);
    }
    default:
      Node.TODO(`${type} @ BitwiseANDExpression`);
  }
  Node.TODO('BitwiseANDExpression');
}

module.exports = BitwiseANDExpression;
