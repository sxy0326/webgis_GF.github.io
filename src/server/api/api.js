// 测试用 api 实例
var models = require('../db');
var express = require('express');
var router = express.Router();
var mysql = require('mysql');
const sql = require('../sqlMap');
// 连接数据库
var conn = mysql.createConnection(models.mysql);
conn.connect();
var jsonWrite = (res, ret) => {
    if (typeof ret === 'undefined') {
        res.json({
            code: '1',
            msg: '操作失败',
        });
    } else {
        res.json(ret);
    }
};
// 增加用户接口
// POST 请求
// router.post('/addUser', (req, res) => {
//     var sql = $sql.user.add;
//     var params = req.body;
//     console.log(params);
//     // ! [params.username, params.age] 自动填充到之前 ？ 里面
//     conn.query(sql, [params.id,params.name, params.age], (err, result) => {
//         if (err) return console.log(err);
//         if (result) {
//             jsonWrite(res, result);
//         }
//     });
// });
// GET 请求
// router.get('/getUser', (req, res) => {
//     let sql = $sql.user.get;
//     let params = req.query;
//     console.log(params);
//     conn.query(sql, (err, result) => {
//         if (err) {
//             console.log(err);
//         }
//         console.log(res, result);
//     });
// });
// module.exports = router;
 
router.get('/list', (req, res) => {
    let sqllist = sql.user.list;    
    // let params = req.body;
    // console.log(params);
    
    conn.query(sqllist, (err, result) => {
        if (err) {
            console.log(err);
        }
        if (result) {
            jsonWrite(res, result);
        }
    });
});
// 登录判断
router.get('/loginUser', (req, res) => {
    var params = req.body;
    var userName = req.query.username;
    var password = req.query.password;
    var sql = "select * from user where username='" + userName + "' and password='" + password + "'";
    console.log(params);
    conn.query(sql, (err, result) => {
        let state = {}
        if (result.length != 0) {
          state.state = 1;
          res.json(state);
          res.end()
        } else {
          state.state = 0;
          res.json(state);
          console.log(result)
        }
    })
});
// 注册判断
router.post("/register",function(req,res){
    var {username,password} = req.body
    var userGetSql = `SELECT * FROM user WHERE username = '${username}'`;
    var addSql = `INSERT INTO user (username,password) VALUES (?,?)`
    //查 query
    conn.query(userGetSql,function (err, result) {
        if (err){
            console.log('查询出错:' + err);
			return;
        };
        console.log("result",result)
        if(result.length==0){
            var inserInfo = [username, password]
            console.log(("注册成功"))
            conn.query(addSql,inserInfo,function (error, result){
                if (error){
                    console.log('注册出错:' + error);
					return;
                };
                res.send({
                    code:200,
                    message:"注册成功"
                })
            })
        }else{
            console.log(("用户已存在"))
            res.send({
                code:0,
                message:"用户已存在"
            })
        }
    });
})
router.post('/select', (req, res) => {
    var params = req.body;
    // console.log(params,1111111)
    var sql = " select * from cover where years = '" + params.id + "'";
    
    conn.query(sql, function(err, result) {
      if (err) {
        console.log(err,2222222);
      }
      if (result) {
        // console.log(res,333333333);
        jsonWrite(res, result);
        // res.json(result);
      }
    })
  });
  router.post('/mapselect', (req, res) => {
    var params = req.body;
    // console.log(params,1111111)
    var sql = " select * from infortable where region = '" + params.id + "'";
    
    conn.query(sql, function(err, result) {
      if (err) {
        console.log(err,2222222);
      }
      if (result) {
        // console.log(res,333333333);
        jsonWrite(res, result);
        // res.json(result);
      }
    })
  });
  
  
module.exports = router;