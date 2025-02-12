# TestSquad - Visual Layout Testing Framework (Boiler-plate)

<p align="center">
  <img src="https://testsquad.co/wp-content/uploads/2022/04 testsquad-opt2-01-scaled-300x71.jpg" alt="TestSquad Logo" width="400"/>
</p>


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

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.x or higher
- Chrome/Firefox browser installed
- Appropriate WebDriver for your browser version

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/testsquad.git
   ```
2. Install dependencies:
  ```bash
   mvn clean install
   ```

### Basic Usage

1. Create a test class extending BaseTest:
   ```java
   public class MyLayoutTest extends BaseTest {
   @Test
   public void testHomepage() {
   driver.get("https://your-website.com");
   checkLayout("specs/homepage.spec", Arrays.asList("desktop", "mobile"));
   }
   }
``
2. Run the tests:
   ```bash
   mvn test
``
3. View the reports:
   ```bash
   open target/galen-reports/index.html
``
## Configuration

### Environment Configuration

Create `config.properties` in `src/test/resources`:
```properties
webdriver.type=chrome
base.url=https://your-website.com
screenshot.dir=target/screenshots
max.retries=3
retry.interval.seconds=2
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