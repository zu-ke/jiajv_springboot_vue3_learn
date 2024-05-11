const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true
})
module.exports = {
    devServer: {
        port: 8084,
        /*解读：加个代理解决跨域*/
        /*proxy: {
            /!*解读：设置拦截器，拦截器格式*!/
            /!*http://localhost:8084/save ==> /api/save*!/
            '/api': {
                /!*解读：代理的目标地址*!/
                target: "http://localhost:8084",
                /!*解读：是否设置同源,是，浏览器允许跨域*!/
                changeOrigin: true,
                /!*解读：路径重写*!/
                pathRewrite: {
                    /!*解读：选择忽略拦截里面的单词*!/
                    '/api': ''
                }
            }
        }*/
    }
}