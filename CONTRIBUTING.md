# Contributing to Beritain

First off, thank you for considering contributing to Beritain! 🎉

## 📋 Code of Conduct

This project and everyone participating in it is governed by our [Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code.

## 🙌 How Can I Contribute?

### Reporting Bugs
- Ensure the bug was not already reported by searching on GitHub under [Issues](https://github.com/yourusername/beritain-compose/issues).
- If you're unable to find an open issue addressing the problem, [open a new one](https://github.com/yourusername/beritain-compose/issues/new). Be sure to include:
  - A clear and descriptive title
  - Steps to reproduce the issue
  - Expected vs actual behavior
  - Screenshots if applicable
  - Device/OS version

### Suggesting Enhancements
- Use GitHub Issues to submit enhancement suggestions
- Clearly describe the enhancement and why it would be useful
- Include screenshots or mockups if applicable

### Pull Requests
1. Fork the repository and create your branch from `main`
2. If you've added code that should be tested, add tests
3. Ensure the test suite passes
4. Make sure your code lints
5. Update the documentation if needed
6. Issue that pull request!

## 🛠 Development Setup

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17
- Android SDK 34

### Setting Up
1. Fork the repository
2. Clone your fork
3. Open the project in Android Studio
4. Sync project with Gradle files
5. Create a `local.properties` file if it doesn't exist and add your API key:
   ```
   NEWS_API_KEY=your_api_key_here
   ```

### Code Style
- Follow the [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use 4 spaces for indentation
- Maximum line length: 100 characters
- Follow the existing code style in the project

### Testing
- Write unit tests for new features
- Ensure all tests pass before submitting a PR
- Follow the Arrange-Act-Assert pattern

## 📝 Pull Request Process

1. Update the README.md with details of changes if needed
2. Ensure your code follows the style guidelines
3. Add tests that prove your fix works or your feature is effective
4. Ensure all tests pass
5. Update the documentation if necessary
6. The PR will be reviewed by the maintainers

## 📜 License

By contributing, you agree that your contributions will be licensed under the project's [MIT License](LICENSE).
