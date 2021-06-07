const Node = require('../Node');

let PropertyDefinition = require('./PropertyDefinition');

// PropertyDefinitionList[Yield, Await] :
//    PropertyDefinition[?Yield, ?Await]
//    PropertyDefinitionList[?Yield, ?Await] , PropertyDefinition[?Yield, ?Await]
let PropertyDefinitionList = (Yield, Await) => (given) => {
  let genChild = PropertyDefinition(Yield, Await);
  let params = [Yield, Await];
  let { properties } = given;
  return Node.fromList('PropertyDefinitionList', properties, genChild, params);
}

module.exports = PropertyDefinitionList;
