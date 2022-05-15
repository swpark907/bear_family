const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    createProxyMiddleware(
      '/restapi',{
      target: 'http://localhost:80',
      changeOrigin: true
    })
  );
  app.use(
    createProxyMiddleware(
      '/authenticate', {
      target: 'http://localhost:80',
      changeOrigin: true,
    }),
  );
  app.use(
    createProxyMiddleware(
      '/signon', {
      target: 'http://localhost:80',
      changeOrigin: true,
    }),
  );
};