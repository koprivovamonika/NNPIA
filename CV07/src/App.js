import './App.css';
import Task from "./task";
import {useState} from "react";
import TaskForm from "./task-form";

function App() {

  const data = [
    {
      id: 0,
      text: "Vytvořit todo list"
    },
    {
      id: 1,
      text: "Nahrát na server"
    }
  ]

  const [todoList, setTodoList] = useState(data);

  const removeHandler = (id) => {
    const filtered = todoList.filter(item => item.id !== id)
    setTodoList(filtered)
  }

  const onNewTaskHandler = (task) =>{
    const newToDo = [...todoList];
    newToDo.push(task);
    setTodoList(newToDo);
  }

  return (
    <div className="App">
      <TaskForm onNewTask={onNewTaskHandler}/>
      {todoList.map(item => <Task key={item.id} task={item} onRemoveHandler={removeHandler}/>)}
      {todoList.length === 0 && <h3>Všechno hotovo</h3>}
    </div>
  );
}

export default App;
