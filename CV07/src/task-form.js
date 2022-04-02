import {useState} from "react";

function TaskForm(props){
    const [text, setText] = useState("");

    const onSubmitHandler = event => {
        event.preventDefault()

        const newTask = {
            id: new Date().getTime(),
            text: text
        }

        props.onNewTask(newTask);
        setText("");
    }

    return (
        <>
            <form onSubmit={onSubmitHandler} className={"form"}>
                <label>Nový úkol:</label>
                <input type={"text"} value={text} onChange={(e) => setText(e.target.value)}/>
                <input type={"submit"} value={"Přidat"}/>
            </form>
        </>

    )
}

export default TaskForm;