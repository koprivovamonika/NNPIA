function Task({task, onRemoveHandler}){
    return(
        <div className={"task"}>
            <h3 key={task.id}>{task.text}</h3>
            <button onClick={() => onRemoveHandler(task.id)}>Hotovo</button>
        </div>
    )
}
export default Task;