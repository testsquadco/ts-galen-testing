<p align="center">
  <img src="https://testsquad.co/wp-content/uploads/2022/04/testsquad-opt2-01-scaled-300x71.jpg" width="400"/>
</p>
# TestSquad - Visual Layout Testing Framework (Boiler-plate)

## About TestSquad

TestSquad is a software testing company specializing in manual and automated testing solutions, ensuring high-quality software for global clients. We provide expert QA services, including mobile automation, to enhance product reliability and performance.

📩 Contact us: info@testsquad.co | 🌐 Website: https://testsquad.co

---

## Overview

This is a powerful visual layout testing framework that combines the robustness of Selenium WebDriver with the precision of Galen Framework. Built for teams who demand pixel-perfect consistency across different devices and browsers.

## 🚀 Features

- 🎯 **Pixel-Perfect Testing**: Ensure your layouts are exactly as designed
- 🔄 **Cross-Browser Consistency**: Test across multiple browsers and viewports
- 📱 **Responsive Design Validation**: Verify layouts across all screen sizes
- 🤖 **Intelligent Retries**: Built-in retry mechanism for flaky tests
- 📸 **Automatic Screenshots**: Visual evidence on test failures
- 📊 **Rich HTML Reports**: Comprehensive test reports with visual comparisons
- ☁️ **Cloud Testing**: Support for Selenium Grid, LambdaTest, BrowserStack, and SauceLabs
- 🔄 **Parallel Execution**: Run tests concurrently across different browsers and platforms

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.x or higher
- Chrome/Firefox/Edge browser installed
- Appropriate WebDriver for your browser version
- Selenium Grid (optional, for grid testing)
- Cloud platform account (optional, for cloud testing)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/testsquad.git
   ```
2. Install dependencies:
   ```bash
   mvn clean install -DskipTests
   ```

### Execution Environments

The framework supports multiple execution environments:

1. **Local Execution**:
   ```bash
   mvn test -Dexecution.environment=local
   ```

2. **Selenium Grid**:
   ```bash
   mvn test \
       -Dexecution.environment=grid \
       -Dgrid.url=http://localhost:4444/wd/hub
   ```

3. **LambdaTest**:
   ```bash
   mvn test \
       -Dexecution.environment=lambdatest \
       -Dlambdatest.username=your_username \
       -Dlambdatest.accesskey=your_access_key
   ```

4. **BrowserStack**:
   ```bash
   mvn test \
       -Dexecution.environment=browserstack \
       -Dbrowserstack.username=your_username \
       -Dbrowserstack.accesskey=your_access_key
   ```

5. **SauceLabs**:
   ```bash
   mvn test \
       -Dexecution.environment=saucelabs \
       -Dsaucelabs.username=your_username \
       -Dsaucelabs.accesskey=your_access_key
   ```

### Additional Test Configuration

```bash
# Specify browser and platform
mvn test \
    -Dbrowser=firefox \
    -Dplatform="Windows 10" \
    -Dbrowser.version=latest

# Run tests in parallel
mvn test -Dparallel.threads=4

# Run tests in headless mode
mvn test -Dheadless=true
```

## Configuration

### Environment Configuration

Create `config.properties` in `src/test/resources`:
```properties
# Execution Configuration
execution.environment=local  # Options: local, grid, lambdatest, browserstack, saucelabs
browser=chrome
headless=false
base.url=https://your-website.com

# Cloud Platform Configuration
grid.url=http://localhost:4444/wd/hub
lambdatest.username=${LAMBDATEST_USERNAME}
lambdatest.accesskey=${LAMBDATEST_ACCESS_KEY}
browserstack.username=${BROWSERSTACK_USERNAME}
browserstack.accesskey=${BROWSERSTACK_ACCESS_KEY}
saucelabs.username=${SAUCELABS_USERNAME}
saucelabs.accesskey=${SAUCELABS_ACCESS_KEY}

# Test Configuration
screenshot.dir=target/screenshots
max.retries=3
retry.interval.seconds=2
parallel.threads=2
```

### Screen Sizes

Default screen sizes are configured in `BaseTest.java`:
- Desktop: 1920x1080
- Tablet: 768x1024
- Mobile: 375x667

## Writing Specs

### Basic Spec Example
```galen
@objects
header css .header
logo css .logo
= Header =
header:
height 80px
contains logo
logo:
inside header 20px left
width 100px
```
## Viewing Reports

The framework generates detailed HTML reports after test execution:

1. Navigate to `target/galen-reports`
2. Open `index.html` in your browser
3. View comprehensive test results including:
    - Test execution summary
    - Screenshots at different breakpoints
    - Layout validation results
    - Visual comparisons
    - Failure highlights

## Best Practices

1. **Organize Specs**:
   - Keep spec files in `src/test/resources/specs`
   - Use meaningful names for spec files
   - Group related checks together

2. **Handle Dynamic Content**:
   - Use approximate (~) checks for dynamic heights
   - Implement wait strategies for loading content
   - Use retry mechanism for unstable elements

3. **Maintenance**:
   - Regular updates of WebDriver versions
   - Keep spec files modular and reusable
   - Document custom tags and object groups

## Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details.

## Support

Need help implementing this framework or looking for custom automation solutions? Contact TestSquad:

- 📧 Email: info@testsquad.co
- 🌐 Website: https://testsquad.co
- 💼 Services: Mobile Testing, Automation Solutions, QA Consulting


## License

TestSquad is released under the [MIT License](LICENSE).

---

<p align="center">Powered by <a href="https://testsquad.co">TestSquad</a> - Your Quality Assurance Partner</p>
