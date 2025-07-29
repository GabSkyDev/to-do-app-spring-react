import "../App.css";

function FilterBar({ onFilterChange }) {
    return (
        <div>
            <h3>Filtrar por Prioridades:</h3>
            <select onChange={e => onFilterChange(e.target.value)}>
            <option value="">Todas</option>
            <option value="LOW">Baixa</option>
            <option value="MEDIUM">Média</option>
            <option value="HIGH">Alta</option>
        </select>
        </div>
    );
}

export default FilterBar;