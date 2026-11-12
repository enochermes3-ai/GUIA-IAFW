package com.guiaia.app;

/**
 * TEXTOS FIJOS DE LA GUÍA. Las herramientas están en app/src/main/assets/herramientas.txt
 * (una por línea: categoría|nombre|plan|precio|puntos separados por ;;|ideal para|enlace|perfil).
 * Perfiles de color: verde, negro, azul, naranja, dorado.
 */
public class Contenido {
    static final String TITULO = "GUÍA DE INTELIGENCIA ARTIFICIAL";
    static final String SUBTITULO = "Las IA's más importantes en cada categoría para que elijas mejor cada vez y seas más eficiente.";

    // {nombre, descripción}
    static final String[][] CATEGORIAS = {
        {"Chatbots y asistentes generales", "El punto de entrada a la IA: conversar, escribir, resolver dudas y coordinar tareas."},
        {"Programación y desarrollo", "De autocompletar líneas a agentes que escriben, prueban y corrigen código solos."},
        {"Investigación y comparación de modelos", "Respuestas con fuentes verificables, y formas de comparar qué IA rinde mejor para cada tarea."},
        {"Creación de imágenes", "De bocetos a piezas listas para publicar, a partir de una descripción en texto."},
        {"Creación de video", "Clips generados o editados con IA — de pago, y sus alternativas gratuitas / de código abierto."},
        {"Música, audio y voz", "Componer canciones completas, clonar voces, generar narración o escribir dictando."},
        {"Productividad y oficina", "IA metida dentro de las herramientas de trabajo diario: notas, docs y reuniones."},
        {"Automatización y agentes", "Conectar aplicaciones y ejecutar tareas repetitivas sin intervención manual."},
        {"IA en tu propia computadora", "Instalá y corré modelos de IA en tu equipo, sin pagar suscripciones ni depender de un servidor."},
        {"Agentes de voz para ventas y llamadas", "IA que atiende o realiza llamadas telefónicas de venta o soporte, con voz natural."},
        {"IA para crear videojuegos", "De la idea al prototipo jugable: ideación, assets 3D y creación de juegos sin (o con poco) código."},
        {"Crear apps", "Plataformas de \"vibe coding\": generan aplicaciones completas — front-end, back-end y base de datos — a partir de una descripción en lenguaje natural."},
        {"Diseño gráfico y edición de fotos", "Diseñar, retocar y mejorar imágenes: desde quitar un fondo hasta crear la identidad de una marca."},
        {"Escritura, traducción y marketing", "Redactar, corregir, traducir y posicionar contenido con ayuda de la IA."},
        {"Educación y aprendizaje", "Aprender idiomas, practicar, resolver ejercicios y estudiar con un tutor de IA siempre disponible."},
    };

    // {color, perfil}  — el color de «Ideal para:» indica el perfil al que mejor encaja cada herramienta
    static final String[][] LEYENDA = {
        {"verde", "Creador de contenido (presupuesto cero)"},
        {"negro", "Creador de contenido (con presupuesto)"},
        {"azul", "Desarrollador / equipo técnico"},
        {"naranja", "Empresa / equipo de ventas"},
        {"dorado", "Estudiante / emprendedor sin conocimientos técnicos"},
    };

    // {tarea, recomendada (de pago), alternativa gratuita}
    static final String[][] COMPARATIVA = {
        {"Chatbot general", "ChatGPT Plus", "Gemini / DeepSeek"},
        {"Programar", "Claude Code", "GitHub Copilot Free"},
        {"Comparar modelos", "—", "LMArena / Design Arena"},
        {"Imágenes", "Midjourney", "Stable Diffusion"},
        {"Video con personajes", "Sora 2 / Kling / Higgsfield", "Wan 2.2 Animate / LTX-2"},
        {"Música con voz", "Suno Pro", "MusicGPT (gratis con compras)"},
        {"Clonar voz", "ElevenLabs", "VibeVoice (dictado)"},
        {"Presentaciones", "Gamma Pro", "Notion AI (básico)"},
        {"Automatizar apps", "Zapier AI", "n8n (self-hosted)"},
        {"IA local", "—", "Pinokio"},
        {"Agente de voz / ventas", "Retell AI / Synthflow", "ChatGPT (Realtime, manual)"},
        {"Crear una app completa", "Base44 / Replit", "Firebase Studio / Glide (free)"},
        {"Diseño y edición de fotos", "Canva Pro / Adobe Firefly", "Upscayl / remove.bg"},
        {"Escribir y marketing", "Jasper / Grammarly Premium", "QuillBot / DeepL (gratis)"},
        {"Estudiar e idiomas", "Duolingo Max / Speak", "Khanmigo / Photomath"},
    };

    // {perfil, combinación recomendada, explicación, color}
    static final String[][] PERFILES = {
        {"Creador de contenido (presupuesto cero)", "Wan 2.2 Animate + Pinokio + Stable Diffusion + VibeVoice", "Cubre animación, imagen, voz y creación local sin pagar una sola suscripción.", "verde"},
        {"Creador de contenido (con presupuesto)", "ChatGPT + Midjourney + Suno + ElevenLabs + Gamma", "Cubre texto, imagen, música, voz y presentaciones sin duplicar herramientas.", "negro"},
        {"Desarrollador / equipo técnico", "Claude + Claude Code + GitHub Copilot + n8n", "Claude para razonar sobre el proyecto completo, Copilot para el día a día, n8n para automatizar procesos internos.", "azul"},
        {"Empresa / equipo de ventas", "Gemini (Workspace) + Retell AI o Synthflow + Zapier AI", "Prioriza integración entre herramientas y atención telefónica automatizada por sobre potencia bruta de un modelo.", "naranja"},
        {"Estudiante", "ChatGPT o Claude (gratis) + NotebookLM + Perplexity", "Gratis en su mayoría; NotebookLM permite estudiar del propio material sin mezclarlo con la web.", "dorado"},
        {"Emprendedor sin conocimientos técnicos", "Base44 + Lovable + Firebase Studio", "Cubre desde el prototipo (Firebase Studio, gratis) hasta la app en producción con backend real (Base44, Lovable), sin escribir código.", "dorado"},
    };
}
