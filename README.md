
# 🎯 School Management System

## Introduction 
EduManageX is a Java-based school management system designed to simplify and organize essential operations for educational institutions. It enables efficient management of students and teachers, tracks financial transactions, and ensures secure user authentication. The project leverages **Object-Oriented Programming (OOP)** principles to deliver a scalable, modular, and user-friendly application.

## 🌍 Integration with the Sustainable Development Goals (SDGs)

EduManageX proudly supports **SDG #4: Quality Education**, which aims to ensure inclusive and equitable quality education and promote lifelong learning opportunities for all.

### ✨ Key Contributions to SDG #4

#### 🟢 Improved Accessibility
- **📘 Streamlined Processes:** EduManageX simplifies administrative tasks, such as tuition management, salary allocation, and student registration, enabling schools to focus on delivering quality education.

#### 🟢 Efficient Financial Tracking
- **💰 Transparent Financial Overview:** The system provides a clear summary of income (from tuition fees) and expenditures (teacher salaries), helping schools allocate resources wisely to enhance educational services.

#### 🟢 Future Potential
- **🚀 Expanding Capabilities:** Planned updates include database integration for better data management and automated notifications for enhanced communication, aiming to further elevate the quality and accessibility of education.

## 🔎 Key Objectives

| No. | Objective |
|-----|-----------|
| 1   | Implement student and teacher registration with unique IDs and secure login management |
| 2   | Provide financial tracking, showing income from student fees and expenditures on teacher salaries |
| 3   | Create a simple and intuitive interface for smooth user interaction |

## 🔬 Scope and Limitations

### Scope ✅
- Student and teacher management
- Financial tracking
- Login security
- Core OOP principles implementation
- User-friendly interface

### Limitations ❌
- No real-time data updates
- Limited financial accounting features
- No external database integration

## 👀 Key Features

- **Student Management**
  - Register and manage students with unique IDs
  - Track tuition payments
  - Display grades

- **Teacher Management**
  - Register and manage teachers with unique IDs
  - Track salary information

- **Financial Overview**
  - View comprehensive financial summaries
  - Track student tuition payments
  - Monitor teacher salaries

- **Security Features**
  - Secure user authentication
  - Password requirements
  - Input validation

## 👨‍💻 Application of OOP Principles

### Encapsulation
- Classes (`Student`, `Teacher`, `School`) encapsulate their attributes and behaviors
- Internal states modified only through defined methods
- Example: Tuition balance updates via `payTuition` method

### Inheritance
- `Person` superclass extended by `Student` and `Teacher` classes
- Common attributes: `id`, `name`, `password`
- Reusable code for improved maintainability

### Polymorphism
- Methods like `calculateSalary` and `calculateTuition` are overridden in specific contexts
- Tailored functionality while maintaining unified interface

### Abstraction
- Essential features abstracted into high-level methods
- Hidden implementation details from user interface
- Simplified user interaction

## 📽️ Console Program Design

![Program Design](https://github.com/user-attachments/assets/e17ec6fe-f598-4876-b6a0-1d39145a63e3)

## 🧠 Algorithm Explanation

- **Object-Oriented Design**: The project applies OOP principles with structured classes (`Student`, `Teacher`, `School`, etc.) that modularize different aspects of the system, making it scalable and maintainable.
- **Error Handling**: Input validation ensures correct data entry and informs users when invalid input is detected, enhancing user experience.

## 🏗 Project Architecture
- **Classes and Relationships:** The project follows a modular OOP design with key classes like `Student`, `Teacher`, and `School`. Each class has specific responsibilities, with a central `SchoolManagementSystem` that manages all instances.

## 🧪 Testing and Debugging
This project underwent rigorous testing to ensure reliability. Manual tests were performed for each feature, including:
- **Student and Teacher Registration:** Tested for unique IDs, name, and password requirements.
- **Financial Summary Calculation:** Validated income and expenditure calculations to confirm accurate financial summaries.
- **Error Handling and Retry Logic:** Ensured all inputs are validated, and users receive informative error messages.

## 🌐 Program User Interface (Prototype)

### User Interface Overview
![UI Overview](https://github.com/user-attachments/assets/38e519d7-b841-4510-b889-71f022bb1c4e)

### Student Interface
![Student Interface 1](https://github.com/user-attachments/assets/58a6727c-49aa-405f-90e0-404f36690413)
![Student Interface 2](https://github.com/user-attachments/assets/0c898692-fcef-4d54-a36b-2f8c491a5b01)

### Teacher Interface
![Teacher Interface 1](https://github.com/user-attachments/assets/16c13529-3a19-4344-b5e1-5de7633fde03)
![Teacher Interface 2](https://github.com/user-attachments/assets/6905f2fe-690e-4077-8cfd-92ccc5560871)

### Financial Summary
![Financial Summary](https://github.com/user-attachments/assets/a21548c9-2cba-4d67-b5c1-960303fadebb)

## 📽 Video Demonstration
[Watch the Demo](https://github.com/user-attachments/assets/f8a004b6-e2a2-4f68-ab20-4797abc63645)

## 💻 Technologies Used

### Programming Language
- **Java**

### Libraries and Tools
- `Swing` - for creating the GUI
- `JDK` - Java Development Kit for compiling and running the program

### Version Control
- **Git** - for version tracking and collaborative development

### Development Environment
- `IntelliJ IDEA` or `Eclipse` - recommended IDEs for Java development

## 🎞 Project Output Simulation

- **Student and Teacher Management**: Users can manage student and teacher profiles with real-time tracking of student fees and teacher salaries.
- **Financial Summary**: Displays an overview of income from student fees versus expenditures, helping visualize the school's financial health.

## 🚀 Usage Instructions

1. **Clone the repository:**
    ```bash
    git clone https://github.com/your-repo/SchoolManagementSystem.git
    ```

2. **Navigate to the project directory:**
    ```bash
    cd SchoolManagementSystem
    ```

3. **Compile and run the project:**
    ```bash
    java MainUI
    ```

## 🚧 Future Enhancements

Planned updates include:
- **Database Integration:** Expand the system to support external databases for more robust data handling
- **Automated Notifications:** Add email/SMS notifications for overdue payments
- **Data Export Options:** Enable exporting data to CSV for easy reporting

## 🤍 Acknowledgements

- **Instructor:** Mr. Owen Patrick Falculan

## ⌨️ Authors

| Name | GitHub | SR Code |
|------|--------|----------|
| 👨‍🎓 Bunquin, Theodore Von Joshua | [BunquinTheodore](https://github.com/BunquinTheodore) | 23-03102 |
