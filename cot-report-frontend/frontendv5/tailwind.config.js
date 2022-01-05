module.exports = {
  mode: 'jit',
  darkMode: 'class',
  purge: {
    enabled: true,
    content: ['./src/**/*.{html,ts}']
  },
  plugins: [require('@tailwindcss/forms')],
}