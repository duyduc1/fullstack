import { defineConfig } from '@vue/cli-service'

export default defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 3002,
  },
  configureWebpack: {
    output: {
      chunkFormat: 'array-push', 
    },
  },
})
