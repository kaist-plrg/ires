const Node = require('../Node');

// GeneratorDeclaration[Yield, Await, Default] :
//    function * BindingIdentifier[?Yield, ?Await] ( FormalParameters[+Yield, ~Await] ) { GeneratorBody }
//    [+Default] function * ( FormalParameters[+Yield, ~Await] ) { GeneratorBody }
let GeneratorDeclaration = (Yield, Await, Default) => (given) => {
  const BindingIdentifier = require('./BindingIdentifier');
  const FormalParameters = require('./FormalParameters');
  const GeneratorBody = require('./GeneratorBody');
  let params = [Yield, Await, Default];
  let { id, body } = given;
  let ps = FormalParameters(true, false)(given.params);
  let b = GeneratorBody(body);
  if (id) {
    let x = BindingIdentifier(Yield, Await)(id);
    return new Node('GeneratorDeclaration', given, 0, [x, ps, b], params);
  } else {
    return new Node('GeneratorDeclaration', given, 1, [ps, b], params);
  }
}

module.exports = GeneratorDeclaration;
