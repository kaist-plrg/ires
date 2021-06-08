const Node = require('../Node');

// EqualityExpression[In, Yield, Await] :
//    RelationalExpression[?In, ?Yield, ?Await]
//    EqualityExpression[?In, ?Yield, ?Await] == RelationalExpression[?In, ?Yield, ?Await]
//    EqualityExpression[?In, ?Yield, ?Await] != RelationalExpression[?In, ?Yield, ?Await]
//    EqualityExpression[?In, ?Yield, ?Await] === RelationalExpression[?In, ?Yield, ?Await]
//    EqualityExpression[?In, ?Yield, ?Await] !== RelationalExpression[?In, ?Yield, ?Await]
let EqualityExpression = (In, Yield, Await) => (given) => {
  let RelationalExpression = require('./RelationalExpression');

  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'ObjectExpression':
    case 'ArrayExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = RelationalExpression(In, Yield, Await)(given);
      return new Node('EqualityExpression', given, 0, [expr], params);
    }
    case 'BinaryExpression': {
      let { index, children } = Node.getBinary(given, ['==', '!=', '===', '!=='],
        EqualityExpression(In, Yield, Await),
        RelationalExpression(In, Yield, Await),
      );
      return new Node('EqualityExpression', given, index, children, params);
    }
    default:
      Node.TODO(`${type} @ EqualityExpression`);
  }
  Node.TODO('EqualityExpression');
}

module.exports = EqualityExpression;
