const argv = require('yargs')
  .scriptName('esparse')
  .usage('Usage: $0 -t [ecmaVersion] [jsFile]')
  .example(
    '$0 -t 2021 sample.js',
    '// Parse JavaScript files in ECMAScript syntax',
  )
  .option('target', {
    alias: 't',
    describe: 'The target version of ECMAScript',
    default: '2021',
    type: 'string',
    nargs: 1,
  })
  .option('detail', {
    alias: 'd',
    describe: 'The detail of parsing process',
    type: 'boolean',
  })
  .alias('help', 'h')
  .version('1.0.0')
  .alias('version', 'v')
  .argv;

let target = argv.target
if (argv._.length !== 1) {
  console.error('Please insert a JavaScript file.');
  process.exit(1);
}
let filename = argv._[0];
let detail = argv.detail;

module.exports = { target, filename, detail };
