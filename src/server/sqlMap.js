// sql语句，在Api中调用
var sqlMap = {
    user: {
    //   add: 'insert ignore into user(userName, password,tel) values (? , ? , ?)',
    //   // 删除用户
    //   del: 'delete from user where id = ? ',
    //   // 查询用户       
    //   valid: 'select * from user where userName = ? and password = ? ',
    //   valid2: 'select * from user where userName = ? ',
    //   // 更新用户信息
    //   update: 'update user set password= ? where userName= ? ',
    //   update2: 'update user set password=?,tel=? where userName=? ',
      // 查询环境数据
      login: 'select * from user where username = ? and password = ?',
      list: 'select * from user',
      register: 'insert ignore into user(username,password) values (?,?)',
    }
  };
  module.exports = sqlMap;
  