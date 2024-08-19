const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  devServer: {
    port: 8081,
  }
}
//module.exports = {
//  configureWebpack: {
//    plugins: [
//      new webpack.DefinePlugin({
//        '__VUE_PROD_HYDRATION_MISMATCH_DETAILS__': JSON.stringify(true)
//      })
//    ]
//  }
//}