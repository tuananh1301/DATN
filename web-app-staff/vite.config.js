import { resolve } from "path";
import { loadEnv } from "vite";
import vue from "@vitejs/plugin-vue";
import vueJsx from "@vitejs/plugin-vue-jsx";
import progress from "vite-plugin-progress";
import {
  createStyleImportPlugin,
  ElementPlusResolve,
} from "vite-plugin-style-import";

const root = process.cwd();

export default ({ command, mode }) => {
  let env;
  const isBuild = command === "build";
  if (!isBuild) {
    env = loadEnv(
      process.argv[3] === "--mode" ? process.argv[4] : process.argv[3],
      root
    );
  } else {
    env = loadEnv(mode, root);
  }
  return {
    base: env.VITE_BASE_PATH,
    plugins: [
      vue({
        script: {
          defineModel: true,
        },
      }),
      vueJsx(),
      progress(),
      env.VITE_USE_ALL_ELEMENT_PLUS_STYLE === "false"
        ? createStyleImportPlugin({
            resolves: [ElementPlusResolve()],
            libs: [
              {
                libraryName: "element-plus",
                esModule: true,
                resolveStyle: (name) => {
                  if (name === "click-outside") {
                    return "";
                  }
                  return `element-plus/es/components/${name.replace(
                    /^el-/,
                    ""
                  )}/style/css`;
                },
              },
            ],
          })
        : undefined,
    ].filter(Boolean),
    resolve: {
      alias: {
        "@": resolve(__dirname, "src"),
      },
    },
  };
};
