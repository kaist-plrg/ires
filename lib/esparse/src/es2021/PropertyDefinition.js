const Node = require('../Node');

// PropertyDefinition[Yield, Await] :
//    IdentifierReference[?Yield, ?Await]
//    CoverInitializedName[?Yield, ?Await]
//    PropertyName[?Yield, ?Await] : AssignmentExpression[+In, ?Yield, ?Await]
//    MethodDefinition[?Yield, ?Await]
//    ... AssignmentExpression[+In, ?Yield, ?Await]
let PropertyDefinition = (Yield, Await) => (given) => {
  Yield, Await, given;
  Node.TODO('PropertyDefinition');
}

module.exports = PropertyDefinition;
