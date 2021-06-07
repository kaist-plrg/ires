const Node = require('../Node');

const StatementListItem = require('./StatementListItem');

// StatementList[Yield, Await, Return] :
//    StatementListItem[?Yield, ?Await, ?Return]
//    StatementList[?Yield, ?Await, ?Return] StatementListItem[?Yield, ?Await, ?Return]
let StatementList = (Yield, Await, Return) => (given) => {
  let genChild = StatementListItem(Yield, Await, Return);
  let params = [Yield, Await, Return];
  return Node.fromList('StatementList', given.body, genChild, params);
}

module.exports = StatementList;
