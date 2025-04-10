//导入Nprogram
import nprogress from "nprogress"
import 'nprogress/nprogress.css'
import axios from 'axios'


//初始化axios
var instance = axios.create({
    //接口地址根目录
    baseURL: 'http://bingjs.com:83',
    //超时时间
    timeout: 20000
});

//创建get请求方法
let get = (url, params) => {
    let {data} = instance.get(url, { params });
    return data;
};

let post = (url, params) => {
    let {data} = instance.pos1(url,params);
    return data;
};

// 添加请求拦截器
instance.interceptors.request.use(
    function (config) {
    // 在发送请求之前做些什么
    nprogress.start();
    return config;
  }, 
    function (error) {
    // 对请求错误做些什么
    nprogress.done();
    return Promise.reject(error);
  });

// 添加响应拦截器
instance.interceptors.response.use(
    function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    nprogress.done();
    return response;
  }, 
    function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    nprogress.done();
    return Promise.reject(error);
  });

  export {
    get,
    post,
    //导出get和post方法
  }