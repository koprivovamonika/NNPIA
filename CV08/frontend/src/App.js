import './App.css';
import Task from "./task";
import {useEffect, useState} from "react";
import TaskForm from "./task-form";

function App() {

  const [todoList, setTodoList] = useState([]);
  const [page, setPage] = useState(0);
  const [maxPage, setMaxPage] = useState(0);
  const [error, setError] = useState();
  const [isPending, setIsPending] = useState(true);

  useEffect(() => {
    pagingHandler();
  }, [page]);


  const pagingHandler = () => {
     fetch(`http://localhost:8080/todos?pageNumber=${page}`,{mode: 'no-cors'})
        .then(response => {
          if (response.ok) {
            return response.json()
          }
          throw new Error(`Unable to get data: ${response.statusText}`)
        })
        .then(json => {setTodoList(json.content); setMaxPage(json.totalPages - 1);})
        .catch((err) => setError(err.message))
        .finally(() => setIsPending(false))
  }

  const removeHandler = (id) => {
      fetch(`http://localhost:8080/todos/${id}`, {
          mode: 'no-cors',
          method: 'DELETE'
      })
          .catch((err) => setError(err.message))
          .finally(() => {pagingHandler();});
  }

  const onNewTaskHandler = (task) =>{
      fetch(`http://localhost:8080/todos`, {
          mode: 'no-cors',
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(task)
      })
          .catch((err) => setError(err.message))
          .finally(() => {
              pagingHandler();
          });
  }

  return (
    <div className="App">
        {isPending && "Loading data..."}
        {error && <div>{error}</div>}
      <TaskForm onNewTask={onNewTaskHandler}/>
      {todoList.map(item => <Task key={item.id} task={item} onRemoveHandler={removeHandler}/>)}
      {todoList.length === 0 && <h3>Všechno hotovo</h3>}
       <hr/>
        <div>
            <button onClick={() => page>0 ? setPage(page => page - 1) : setPage(0)}>Prev</button>
            <b style={{margin:"5px", padding:"5px", textAlign: "center"}}>{page}</b>
            <button onClick={() => page<maxPage ? setPage(page => page + 1) : setPage(maxPage)}>Next</button>
        </div>
    </div>
  );
}

export default App;
