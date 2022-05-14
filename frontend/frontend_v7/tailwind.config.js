const defaultTheme = require('tailwindcss/defaultTheme');

module.exports = {
  mode: 'jit',
  content: [
    "./src/**/*.{html,ts}",
  ],
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
  purge: {
    enabled: true,
    content: ['./src/**/*.{html,ts}']
  },
  plugins: [require('@tailwindcss/forms')],
}
