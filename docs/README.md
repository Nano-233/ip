# 🌙 Luna Task Manager - User Guide 🚀  
**A sleek CLI task manager to keep your life organized with ease!**  

---

## 🎯 Introduction  
Luna Task Manager is a simple yet powerful **command-line** task organizer. Whether you're juggling deadlines, planning events, or just need to keep track of tasks, Luna helps you manage everything efficiently. With intuitive commands and automatic saving, you'll never lose track of your to-dos again!  

> [!TIP]
> Luna **auto-saves** your tasks, so you won’t lose progress even if you close the app!  

---

## 📥 Installation  
1. **Ensure you have Java 11 or above** installed on your machine.  
2. **Download** `Luna.jar` from the latest release.  
3. Open a terminal and run:  

`java -jar Luna.jar`

You're all set! 🎉  

---

## 📌 Features  

### 📋 1. Adding Tasks  
Luna supports **three types** of tasks:  
- **To-Do** → Simple tasks with no date attached.  
`todo Bake a cake`
_✓ Adds a task: "Bake a cake"._  
- **Deadline** → Tasks with a due date.  
`deadline Submit report /by 30/05/2025`
_✓ Adds "Submit report" due on May 30, 2025._  
- **Event** → Tasks with a start and end date.  
`event F1 Singapore /from 03/05/2025 /to 10/05/2025`
_✓ Schedules "F1 Singapore" from May 3 to May 10._  

---

### ✅ 2. Managing Tasks  
- **Mark a task as done**  
`mark 2`_✓ Marks task #2 as completed._  
- **Unmark a task**  
`unmark 2`
_✓ Marks task #2 as **not done**._  
- **Delete a task**  
`delete 3`
_✓ Removes task #3._  
- **Clear all tasks**  
`delete all`
_💥 Deletes ALL tasks (be careful)!_  

---

### 🔎 3. Viewing & Searching  
- **List all tasks**  
`list`
_✓ Displays all tasks and their ids._  
- **Find tasks by keyword**  
`find book`
_✓ Searches for tasks containing "book"._  

---

### 🏷 4. Organizing with Tags  
- **Tag a task**  
`tag 1 urgent`
_✓ Adds the tag `urgent` to task #1._ 

---

## ⚙️ Under the Hood  
Luna follows Object-Oriented Programming principles to ensure efficient task handling.  

```java
// Initializes Luna with stored data
public static void main(String[] args) {
  new Luna("data/Luna.txt").run();
}
```
## 🎉 Why Luna?  
✔ Fast & lightweight ⚡  
✔ Auto-saves progress 📝  
✔ Simple & intuitive commands 💡  

> Ready to **boost your productivity**? Start using Luna today! 🚀  