/**
 * Created by tang on 2017/5/25.
 */

module.exports = {
    entry: {
        index: './src/index.js',
        cart: './src/cart.js',
        space: './src/space.js',
        signup: './src/signup.js'
    },
    output: {
        path: __dirname + '/../src/main/resources/public/js',
        publicPath: '/public/js/',
        filename: '[name].js'
    },
    module: {
        loaders: [
            {
                test: /\.js$/,
                loader: 'babel-loader',  // Transform it with babel
                exclude: /node_modules/  // don't transform node_modules folder (which don't need to be compiled)
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader',
            }
        ]
    },
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.esm.js'
        }
    }
};