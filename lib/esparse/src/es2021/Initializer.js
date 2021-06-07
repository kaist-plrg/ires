const Node = require('../Node');

let AssignmentExpression = require('./AssignmentExpression');

// Initializer[In, Yield, Await] :
//    = AssignmentExpression[?In, ?Yield, ?Await]
let Initializer = (In, Yield, Await) => (given) => {
  let expr = AssignmentExpression(In, Yield, Await)(given);
  let params = [In, Yield, Await];
  return new Node('Initializer', given, 0, [expr], params);
}

module.exports = Initializer;
