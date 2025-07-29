import { useState } from "react";
import TaskList from "./components/TaskList";
import TaskForm from "./components/TaskForm";
import FilterBar from "./components/FilterBar";
import "./App.css";

function App() {
  const [filter, setFilter] = useState("");
  const [editedTask, setEditedTask] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const recarregarTarefas = () => setRefresh(!refresh);
  const iniciarEdicao = (task) => setEditedTask(task);
  const cancelarEdicao = () => setEditedTask(null);

  return (
    <div className="app-container">
      <main className="main-content">
        <h1>Lista de Tarefas</h1>
        <TaskList filter={filter} onEdit={iniciarEdicao} key={refresh} />
      </main>

      <aside className="sidebar">
        <h1>Gerenciar Tarefas</h1>
        <TaskForm
          onSave={recarregarTarefas}
          editedTask={editedTask}
          cancelEdit={cancelarEdicao}
        />
        <FilterBar onFilterChange={setFilter} />
      </aside>
    </div>

  );
}

export default App;
