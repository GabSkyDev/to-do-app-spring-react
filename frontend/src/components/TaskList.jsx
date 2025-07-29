import { useEffect, useState } from "react";
import api from "../api";

function TaskList({ filter, onEdit }) {
    const [tasks, setTasks] = useState([]);

    const loadTasks = () => {
        api
            .get("/task")
            .then((response) => {
                const allTasks = response.data;
                if (filter) {
                    setTasks(allTasks.filter((t) => t.priority === filter));
                } else {
                    setTasks(allTasks);
                }
            })
            .catch((error) => console.error("Erro ao carregar tarefas", error));
    };

    useEffect(() => {
        loadTasks();
    }, [filter]);

    const deleteTask = (id) => {
        api.delete(`/task/${id}`).then(() => loadTasks());
    };

    const formatPriority = (priority) => {
        switch (priority) {
            case "LOW":
                return "Baixa";
            case "MEDIUM":
                return "Média";
            case "HIGH":
                return "Alta";
            default:
                return priority;
        }
    }

    return (
        <div>
            {tasks.map((task) => {
                // Classes de cor por prioridade e estado
                const priorityClass =
                    task.priority === "HIGH"
                        ? "priority-high"
                        : task.priority === "MEDIUM"
                            ? "priority-medium"
                            : "priority-low";

                const stateClass = task.state ? "status-done" : "status-pending";

                return (
                    <li key={task.id}>
                        <div className="task-header">
                            <h1>{task.name}</h1>
                            <div className="task-tags">
                                <span className={priorityClass}>{formatPriority(task.priority)}</span>
                                <span className={stateClass}>{task.state ? "Concluída" : "Pendente"}</span>
                            </div>
                        </div>

                        <h3>{task.description}</h3>

                        <div className="task-buttons">
                            <button onClick={() => deleteTask(task.id)} style={{ background: "#b71c1c" }}>Excluir</button>
                            <button onClick={() => onEdit(task)}>Editar</button>
                        </div>
                    </li>
                );
            })}
        </div>
    );
}

export default TaskList;
