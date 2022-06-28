module.exports = {
  css: {
    loaderOptions: {
      css: {
        sourceMap: process.env.NODE_ENV !== 'production' ? true : false,
      },
    },
  },
  devServer: {
    overlay: false,
    proxy: {
      '/ff': {
        target: 'http://localhost:80',
        ws: true,
        changeOrigin: true,
      },
    },
  },
};
