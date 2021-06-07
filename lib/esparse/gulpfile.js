const {task, src, dest, lastRun, watch, series} = require('gulp');
const eslint = require('gulp-eslint');
const mocha = require('gulp-mocha');
const uglify = require('gulp-uglify');

const src_files = 'src/**/*.js';
const test_files = 'test/**/*.test.js';

task('pass', function() {
  return src(src_files)
    .pipe(dest('dist'))
});

task('compress', function() {
  return src(src_files)
    .pipe(uglify())
    .pipe(dest('dist'))
});

task('eslint', function() {
  return src(src_files, {since: lastRun('eslint')})
    .pipe(eslint())
    .pipe(eslint.format())
});

task('test', function() {
  return src(test_files, {read: false})
    .pipe(mocha())
    .on('error', (error) => {
      console.error(error.message);
      console.error(error.stack);
    })
});

task('watch', function() {
  return watch([src_files, test_files], series('pass', 'eslint', 'test'));
});

task('default', series('pass', 'eslint', 'test', 'watch'));
