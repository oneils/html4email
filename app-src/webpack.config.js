module.exports = {
    entry: [
        './app/react/core.js'
    ],
    output: {
        path: __dirname + '/app',
        publicPath: '/',
        filename: 'bundle.js'
    }
};