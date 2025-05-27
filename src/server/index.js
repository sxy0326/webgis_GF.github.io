// 使用 express 创建服务器
 // 导入 express 模块
 const express = require('express');
 // 调用 express 函数，返回一个数据库实例
 const app = express();
 // 导入路由模块
 const userApi = require('./api/api');
 // 端口号
 const port = 9999;
 // 注册全局解析中间件
 app.use(express.json());
 app.use(express.urlencoded({ extended: false }));
 // 注册路由模块
 app.use('/api/user', userApi);
 // 调用 app.listen() 启动服务器
 app.listen(port, () => console.log(`Example app listening on port 9999!`));
//slqMap.js 写入 sql 语句
// todo sql 语句留后面调用
// module.exports = {
//     user: {
//         // ? 占位符 后面给数据自动填充
//         add: 'insert into te_user(id,name, age) values(?,?,?)',
//         get: "select * from user",
//     },
// };