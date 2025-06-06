# Lean QA Dashboard (Startup QA Tool)

This is a lightweight QA dashboard tool built in Java. It simulates startup-style UAT tracking by visualizing test case results using a pie chart. Inspired by actual UAT work for AI-based investment and e-commerce platforms.

## 💻 Tech Stack
- Java 22 + JavaFX
- Apache POI (Excel reading)
- Maven

## 📈 Features
- Parse Excel test results
- Display summary pie chart (Pass / Fail / Skipped)
- Works standalone — no environment or server needed
- Easy to extend for PDFs, APIs, DB queries

## 🧪 How to Run
1. Clone repo
2. Add `exploratory_testing.xlsx` in `results/`
3. Run with Maven:
```bash
mvn clean javafx:run
