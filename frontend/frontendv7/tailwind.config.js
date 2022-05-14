const { guessProductionMode } = require("@ngneat/tailwind");
const defaultTheme = require('tailwindcss/defaultTheme');

process.env.TAILWIND_MODE = guessProductionMode() ? 'build' : 'watch';

module.exports = {
    prefix: '',
    mode: 'jit',
    purge: {
      content: [
        './src/**/*.{html,ts,css,scss,sass,less,styl}',
      ]
    },
    darkMode: 'class',
    theme: {
      extend: {
        fontFamily: {
          qs: ['Quicksand', ...defaultTheme.fontFamily.sans],
        },
        backgroundImage: (theme) => ({
          'gorilla-light':
            "url('~/assets/images/logo-gorilla-light.PNG')",
          'gorilla-dark':
          "url('~/assets/images/logo-gorilla.PNG')",
        })
      },
    },
    variants: {
      extend: {},
    },
    plugins: [require('@tailwindcss/aspect-ratio'),require('@tailwindcss/forms'),require('@tailwindcss/line-clamp'),require('@tailwindcss/typography')],
};
