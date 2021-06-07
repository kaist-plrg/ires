const Node = require('../Node');

let ShiftExpression = require('./ShiftExpression');

// RelationalExpression[In, Yield, Await] :
//    ShiftExpression[?Yield, ?Await]
//    RelationalExpression[?In, ?Yield, ?Await] < ShiftExpression[?Yield, ?Await]
//    RelationalExpression[?In, ?Yield, ?Await] > ShiftExpression[?Yield, ?Await]
//    RelationalExpression[?In, ?Yield, ?Await] <= ShiftExpression[?Yield, ?Await]
//    RelationalExpression[?In, ?Yield, ?Await] >= ShiftExpression[?Yield, ?Await]
//    RelationalExpression[?In, ?Yield, ?Await] instanceof ShiftExpression[?Yield, ?Await]
//    [+In] RelationalExpression[+In, ?Yield, ?Await] in ShiftExpression[?Yield, ?Await]
let RelationalExpression = (In, Yield, Await) => (given) => {
  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = ShiftExpression(Yield, Await)(given);
      return new Node('RelationalExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ RelationalExpression`);
  }
  Node.TODO('RelationalExpression');
}

module.exports = RelationalExpression;
