package com.kodeleku.project_gestor.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kodeleku.project_gestor.R
import com.kodeleku.project_gestor.models.Project

class ProjectAdapter(
    private val projects: List<Project>,
    private val languages: Map<Int, String>, // Mapa de id -> nombre del lenguaje
    private val onClick: (Project) -> Unit
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        holder.bind(project, languages) // Pasa el mapa de lenguajes al método bind
        holder.itemView.setOnClickListener { onClick(project) }
    }

    override fun getItemCount(): Int = projects.size

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val projectName: TextView = itemView.findViewById(R.id.tv_project_name)
        private val projectLanguage: TextView = itemView.findViewById(R.id.tv_project_language)
        private val projectPriority: TextView = itemView.findViewById(R.id.tv_project_priority)

        @SuppressLint("SetTextI18n")
        fun bind(project: Project, languages: Map<Int, String>) {
            val context = itemView.context
            val languageName = languages[project.languageId] ?: context.getString(R.string.unknown_language)
            projectName.text = project.name
            projectLanguage.text = context.getString(R.string.project_language, languageName)
            projectPriority.text = context.getString(R.string.project_priority, project.priority)
        }
    }
}

