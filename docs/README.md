# Speed - Your Task Manager 🚀

![Speed UI](Ui.png)

Speed is a simple, friendly chatbot that helps you manage your tasks using commands.

---

## Features 📝

Speed helps you organize three types of tasks:

- **Todo [T]** — Simple tasks without deadlines
- **Deadline [D]** — Tasks with a specific deadline
- **Event [E]** — Tasks that span a time period

---

## Getting Started

When you start Speed, you'll see a greeting. Just type commands to manage your tasks. Speed automatically saves everything, so you won't lose your data.

---

## Commands 💬

### Adding Tasks

**Add a Todo**
```
todo <description>
```
Example: `todo read book`  
Creates a simple task with no date.

**Add a Deadline**
```
deadline <description> /by <date>
```
Example: `deadline submit report /by 2026-07-15`  
Creates a task due on a specific date. Use format: `YYYY-MM-DD`

**Add an Event**
```
event <description> /from <start-date> /to <end-date>
```
Example: `event summer camp /from 2026-07-01 /to 2026-07-10`  
Creates a task spanning multiple days. Use format: `YYYY-MM-DD`

### Managing Tasks

**View All Tasks**
```
list
```
Shows all your tasks with numbers. Use the numbers with other commands.

**Mark Task as Done**
```
mark <number>
```
Example: `mark 2`  
Marks task 2 as complete (shows [x]).

**Mark Task as Not Done**
```
unmark <number>
```
Example: `unmark 2`  
Marks task 2 as incomplete (shows [ ]).

**Delete a Task**
```
delete <number>
```
Example: `delete 3`  
Removes task #3 from your list.

**Search for Tasks**
```
find <keyword>
```
Example: `find book`  
Shows all tasks containing "book".

**View Tasks on a Specific Date**
```
view_schedule <date>
```
Example: `view_schedule 2026-07-15`  
Shows deadlines and events on that date. Use format: `YYYY-MM-DD`

**Exit**
```
bye
```
Closes Speed and saves all your tasks.

---

## Date Format 📅

Always use this format for dates: `YYYY-MM-DD`

✅ **Correct:** `2026-07-15`  
❌ **Wrong:** `15-07-2026`, `07/15/2026`, `July 15 2026`

When you view tasks, dates display as `Jul 15 2026` for readability.

---

## Quick Reference

| What You Want | Command |
|---|---|
| See all tasks | `list` |
| Add a simple task | `todo walk the dog` |
| Add a deadline | `deadline pay rent /by 2026-08-01` |
| Add an event | `event meeting /from 2026-07-20 /to 2026-07-21` |
| Mark #2 done | `mark 2` |
| Mark #2 not done | `unmark 2` |
| Remove #3 | `delete 3` |
| Find tasks | `find homework` |
| See July 15 tasks | `view_schedule 2026-07-15` |
| Save & exit | `bye` |

---

## Tips 💡

- Task numbers in the `list` view start from 1. Use these numbers with `mark`, `unmark`, and `delete`.
- Dates must always follow `YYYY-MM-DD` format or Speed will reject it.
- For events and deadlines, make sure the end date is not before the start date.
- Everything is saved automatically when you exit with `bye`.

---

## Need Help?

If you see an error message:
- **"Invalid input!"** — You typed a command Speed doesn't recognize. Check the command format above.
- **"Date format is invalid!"** — Use `YYYY-MM-DD` format for dates.
- **"End date cannot be before start date!"** — For events, the end date must be the same as or later than the start date.
- **"Index out of bound!"** — The task number doesn't exist. Use `list` to see valid numbers.

---

*Organize your tasks, stay on track! 🎯*