module.exports = {
    entry: [
        './app/react/core.jsx'
    ],
    module: {
        loaders: [{
            test: /\.jsx?$/,
            exclude: /node_modules/,
            loader: 'babel'
        }]
    },
    resolve: {
        extensions: ['', '.js', '.jsx']
    },
    output: {
        path: __dirname + '/app',
        publicPath: '/',
        filename: 'bundle.js'
    }
};