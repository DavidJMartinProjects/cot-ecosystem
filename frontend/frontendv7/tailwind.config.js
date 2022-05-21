const defaultTheme = require('tailwindcss/defaultTheme');

module.exports = {
  mode: 'jit',
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
    extend: {
      backgroundImage: ['dark'],
    }
  },
  content: ['./src/**/*.{html,js}', './node_modules/tw-elements/dist/js/**/*.js'],
  purge: {
    enabled: true,
    content: ['./src/**/*.{html,ts}']
  },
  plugins: [require('@tailwindcss/forms'), require('@themesberg/flowbite/plugin'), require('tw-elements/dist/plugin')],
}
