import "../App.css";

import { useEffect, useState } from "react";
import api from "../api";

function TaskForm({ onSave, editedTask, cancelEdit }) {
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [priority, setPriority] = useState("LOW");
    const [state, setState] = useState(false);

    useEffect(() => {
        if (editedTask) {
            setName(editedTask.name);
            setDescription(editedTask.description);
            setPriority(editedTask.priority);
            setState(editedTask.state);
        }
    }, [editedTask]);

    const handleSubmit = (e) => {
        e.preventDefault();
        const taskData = { name, description, priority, state };

        if (editedTask && editedTask.id) {
            // PUT
            api.put(`/task/${editedTask.id}`, taskData)
                .then(() => {
                    onSave();
                    cancelEdit();
                });
        } else {
            // POST
            api.post("/task", taskData)
                .then(() => {
                    setName("");
                    setDescription("");
                    setPriority("LOW");
                    setState(false);
                    onSave();
                });
        }
    }

    return (
        <form onSubmit={handleSubmit} className={editedTask ? "edit-mode" : ""}>
            {editedTask && <h3 style={{ color: "#ff6f00" }}>Editando Tarefa</h3>}
            <input
                value={name}
                onChange={e => setName(e.target.value)}
                placeholder="Nome da tarefa"
                required
            />
            <input
                value={description}
                onChange={e => setDescription(e.target.value)}
                placeholder="Descrição"
            />
            <select value={priority} onChange={e => setPriority(e.target.value)}>
                <option value="HIGH">Alta</option>
                <option value="MEDIUM">Média</option>
                <option value="LOW">Baixa</option>
            </select>
            <label>
                <input
                    type="checkbox"
                    checked={state}
                    onChange={e => setState(e.target.checked)}
                />
                Concluída
            </label>
            <button
                type="submit"
                className={editedTask ? "btn-edit" : ""}
            >
                {editedTask ? "Atualizar" : "Salvar"}
            </button>

            {editedTask && (
                <button type="button" onClick={cancelEdit}>Cancelar</button>
            )}
        </form>
    )
}

export default TaskForm;