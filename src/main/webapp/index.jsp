<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><html class="light" lang="en" style=""><head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>Atlas Freight - Upload Files</title>
<!-- Fonts & Icons -->
<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&amp;family=Inter:wght@300;400;500;600&amp;display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap" rel="stylesheet">
<script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
<script id="tailwind-config">
        tailwind.config = {
          darkMode: "class",
          theme: {
            extend: {
              "colors": {
                      "on-surface-variant": "#444650",
                      "on-primary": "#ffffff",
                      "outline-variant": "#c5c6d2",
                      "surface-bright": "#f7fafc",
                      "error-container": "#ffdad6",
                      "on-background": "#181c1e",
                      "on-tertiary-fixed": "#2f1500",
                      "on-surface": "#181c1e",
                      "on-tertiary": "#ffffff",
                      "error": "#ba1a1a",
                      "primary-fixed-dim": "#b3c5ff",
                      "background": "#f7fafc",
                      "inverse-surface": "#2d3133",
                      "secondary-fixed-dim": "#a9c7ff",
                      "surface-container-low": "#f1f4f6",
                      "secondary": "#005db6",
                      "on-secondary-container": "#00376f",
                      "surface-variant": "#e0e3e5",
                      "on-secondary-fixed-variant": "#00468c",
                      "tertiary-fixed": "#ffdcc3",
                      "surface-container-highest": "#e0e3e5",
                      "on-secondary": "#ffffff",
                      "surface-container-lowest": "#ffffff",
                      "inverse-primary": "#b3c5ff",
                      "surface": "#f7fafc",
                      "on-tertiary-fixed-variant": "#6e3900",
                      "primary-container": "#002366",
                      "on-tertiary-container": "#d97600",
                      "secondary-container": "#63a1ff",
                      "surface-container": "#ebeef0",
                      "primary": "#00113a",
                      "on-error-container": "#93000a",
                      "tertiary": "#240f00",
                      "on-primary-fixed": "#00174a",
                      "on-primary-container": "#758dd5",
                      "on-secondary-fixed": "#001b3d",
                      "outline": "#757682",
                      "surface-dim": "#d7dadc",
                      "surface-container-high": "#e5e9eb",
                      "on-error": "#ffffff",
                      "secondary-fixed": "#d6e3ff",
                      "primary-fixed": "#dbe1ff",
                      "on-primary-fixed-variant": "#2a4386",
                      "surface-tint": "#435b9f",
                      "tertiary-container": "#422000",
                      "tertiary-fixed-dim": "#ffb77d",
                      "inverse-on-surface": "#eef1f3"
              },
              "borderRadius": {
                      "DEFAULT": "0.125rem",
                      "lg": "0.25rem",
                      "xl": "0.5rem",
                      "full": "0.75rem"
              },
              "fontFamily": {
                      "headline": ["Manrope"],
                      "body": ["Inter"],
                      "label": ["Inter"]
              }
            },
          },
        }
      </script>
<style>
        .material-symbols-outlined {
            font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
        }
        body { font-family: 'Inter', sans-serif; }
        h1, h2, h3, .font-manrope { font-family: 'Manrope', sans-serif; }
    </style>
</head>
<body class="bg-surface text-on-surface">
<!-- SideNavBar -->
<aside class="hidden md:flex flex-col h-screen w-64 fixed left-0 top-0 bg-[#f1f4f6] dark:bg-slate-900 z-50 overflow-y-auto p-6">
<div class="flex items-center gap-3 mb-10">
<div class="w-10 h-10 bg-primary rounded-lg flex items-center justify-center text-white">
<span class="material-symbols-outlined">local_shipping</span>
</div>
<div>
<h2 class="text-xl font-extrabold text-[#00113a] dark:text-white uppercase tracking-widest leading-tight">Atlas Frete</h2>
<p class="text-[10px] font-manrope tracking-widest uppercase font-semibold text-slate-500">Plataforma de Gestão Logística</p>
</div>
</div>
<nav class="space-y-2">
<a class="flex items-center gap-3 px-4 py-3 bg-[#ffffff] dark:bg-slate-800 text-[#00113a] dark:text-blue-400 font-bold rounded-lg shadow-sm font-manrope text-sm tracking-tight transition-all scale-98 active:opacity-80" href="#">
<span class="material-symbols-outlined">upload_file</span>
            Upload de Arquivos
        </a>
<a class="flex items-center gap-3 px-4 py-3 text-slate-500 dark:text-slate-400 hover:bg-slate-200 dark:hover:bg-slate-800 font-manrope text-sm tracking-tight transition-colors duration-200 rounded-lg" href="${pageContext.request.contextPath}/results">
<span class="material-symbols-outlined">analytics</span>
            Resumo da Exportação
        </a>
</nav>
</aside>
<div class="md:ml-64 min-h-screen flex flex-col">
<!-- TopNavBar -->
<header class="bg-[#f7fafc]/80 dark:bg-slate-950/80 backdrop-blur-xl fixed top-0 right-0 left-64 h-16 z-40 px-8 flex justify-between items-center">
<div class="flex items-center gap-8">
<span class="text-lg font-black text-[#00113a] dark:text-white font-manrope">Atlas Frete</span>
</div>
<div class="flex items-center gap-4">
<button class="p-2 text-slate-500 dark:text-slate-400 hover:text-[#005db6] dark:hover:text-blue-300 transition-all opacity-90 hover:opacity-100">

</button>
<button class="p-2 text-slate-500 dark:text-slate-400 hover:text-[#005db6] dark:hover:text-blue-300 transition-all opacity-90 hover:opacity-100">

</button>
</div>
</header>
<!-- Main Content Area -->
<main class="flex-1 p-8 lg:p-12 max-w-7xl mx-auto w-full mt-16">
<!-- Hero Header Section -->
<div class="mb-12 relative">
<div class="flex items-end justify-between gap-8 flex-wrap">
<div class="max-w-2xl">
<h1 class="text-5xl font-extrabold text-primary font-manrope tracking-tighter mb-4">Upload de Dados Logísticos</h1>
<p class="text-on-surface-variant text-lg font-body leading-relaxed">Inicialize seu motor de cálculo de frete. Faça o upload dos parâmetros operacionais da sua empresa e do backlog de remessas para iniciar o processamento das rotas.</p>
</div>
<div class="hidden xl:block h-32 w-32 opacity-10">
<span class="material-symbols-outlined text-[120px] text-primary">upload_file</span>
</div>
</div>
</div>
<!-- Bento-style Upload Zones -->
<div class="grid grid-cols-1 lg:grid-cols-12 gap-8 mb-12">
<!-- Zone 1: Configuração da Companhia -->
<div class="lg:col-span-5 flex flex-col group">
<div class="bg-surface-container-lowest p-8 rounded-xl shadow-sm border border-transparent hover:border-primary/10 transition-all duration-300 flex-1 flex flex-col">
<div class="flex flex-col h-full">
<!-- Header Section -->
<div class="flex flex-col mb-8 h-[120px]">
<div class="flex items-center justify-between mb-4">
<span class="bg-primary-container/10 text-primary-container px-3 py-1 rounded-full text-[10px] font-bold tracking-widest uppercase font-manrope">configuração do sistema</span>

</div>
<h3 class="text-2xl font-bold text-primary font-manrope mb-2">Configuração da Companhia</h3>
<p class="text-[11px] text-on-surface-variant leading-relaxed">Inicialize seu motor de cálculo de frete. Faça o upload dos parâmetros operacionais da sua empresa e do backlog de remessas para iniciar o processamento das rotas.</p>
</div>
<!-- Upload Area -->
<div class="mb-8 h-[160px] flex flex-col justify-center">
<label class="cursor-pointer flex flex-col items-center justify-center border-2 border-dashed border-outline-variant rounded-lg bg-surface-container-low hover:bg-white hover:border-primary transition-all p-8 group/upload h-full">
<input class="hidden" type="file">
<span class="material-symbols-outlined text-3xl text-outline mb-2 group-hover/upload:text-primary transition-colors">cloud_upload</span>
<span class="text-xs font-semibold text-primary">Clique para enviar configurações</span>
</label>
</div>
<!-- Preview Table Section -->
<div class="overflow-hidden border border-outline-variant rounded-lg mt-auto">
<div class="bg-surface-container-low px-4 py-2 border-b border-outline-variant text-[10px] font-bold uppercase tracking-wider text-on-surface-variant">estrutura do arquivo de configuração</div>
<table class="w-full text-left text-xs font-body">
<thead class="bg-surface-container px-4">
<tr>
<th class="px-4 py-2 font-bold text-primary">parâmetro</th>
<th class="px-4 py-2 font-bold text-primary">valor</th>
</tr>
</thead>
<tbody class="divide-y divide-outline-variant">
<tr>
<td class="px-4 py-2 text-on-surface-variant">distace_factor</td>
<td class="px-4 py-2 font-mono">0.05</td>
</tr>
<tr>
<td class="px-4 py-2 text-on-surface-variant">weight_factor</td>
<td class="px-4 py-2 font-mono">2.10</td>
</tr>
<tr>
<td class="px-4 py-2 text-on-surface-variant">express_factor</td>
<td class="px-4 py-2 font-mono">1.5</td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</div>
<!-- Zone 2: Lista de Pedidos -->
<div class="lg:col-span-7 flex flex-col group">
<div class="bg-surface-container-lowest p-8 rounded-xl shadow-sm border border-transparent hover:border-primary/10 transition-all duration-300 flex-1 flex flex-col relative">
<!-- Decorative background texture -->
<div class="absolute -right-12 -top-12 w-64 h-64 bg-primary/5 rounded-full blur-3xl pointer-events-none"></div>
<div class="relative z-10 flex flex-col h-full">
<!-- Header Section -->
<div class="flex flex-col mb-8 h-[120px]">
<div class="flex items-center justify-between mb-4">
<span class="bg-secondary-container/20 text-on-secondary-container px-3 py-1 rounded-full text-[10px] font-bold tracking-widest uppercase font-manrope">Dados operacionais</span>

</div>
<h3 class="text-2xl font-bold text-primary font-manrope mb-2">Lista de Pedidos</h3>
<p class="text-[11px] text-on-surface-variant leading-relaxed">Inicialize seu motor de cálculo de frete. Faça o upload dos parâmetros operacionais da sua empresa e do backlog de remessas para iniciar o processamento das rotas.</p>
</div>
<!-- Upload Area -->
<div class="mb-8 h-[160px] flex flex-col justify-center">
<label class="cursor-pointer flex flex-col items-center justify-center border-2 border-dashed border-outline-variant rounded-lg bg-surface-container-low hover:bg-white hover:border-secondary transition-all py-8 group/upload h-full">
<input class="hidden" type="file">
<div class="relative mb-2">
<span class="material-symbols-outlined text-3xl text-outline group-hover/upload:text-secondary transition-colors">upload_file</span>
<div class="absolute -bottom-1 -right-1 w-3 h-3 bg-secondary rounded-full flex items-center justify-center">
<span class="material-symbols-outlined text-[8px] text-white">add</span>
</div>
</div>
<span class="text-xs font-semibold text-primary">Enviar lista de pedidos</span>
</label>
</div>
<!-- Preview Table Section -->
<div class="overflow-x-auto border border-outline-variant rounded-lg mt-auto">
<div class="bg-surface-container-low px-4 py-2 border-b border-outline-variant text-[10px] font-bold uppercase tracking-wider text-on-surface-variant">estrutura da lista de pedidos</div>
<table class="w-full text-left text-[11px] font-body min-w-[500px]">
<thead class="bg-surface-container">
<tr>
<th class="px-4 py-2 font-bold text-primary">order_id</th>
<th class="px-4 py-2 font-bold text-primary">cliente</th>
<th class="px-4 py-2 font-bold text-primary">distância</th>
<th class="px-4 py-2 font-bold text-primary">peso</th>
<th class="px-4 py-2 font-bold text-primary">serviço</th>
</tr>
</thead>
<tbody class="divide-y divide-outline-variant">
<tr>
<td class="px-4 py-2 text-primary font-mono">ORD-001</td>
<td class="px-4 py-2">Loja Tech</td>
<td class="px-4 py-2">450.0</td>
<td class="px-4 py-2">2.5</td>
<td class="px-4 py-2">NORMAL</td>
</tr>
<tr>
<td class="px-4 py-2 text-primary font-mono">ORD-002</td>
<td class="px-4 py-2">Maria Silva</td>
<td class="px-4 py-2">120.0</td>
<td class="px-4 py-2">0.8</td>
<td class="px-4 py-2">EXPRESS</td>
</tr>
<tr>
<td class="px-4 py-2 text-primary font-mono">ORD-003</td>
<td class="px-4 py-2">Construtora XYZ</td>
<td class="px-4 py-2">1200.0</td>
<td class="px-4 py-2">50.0</td>
<td class="px-4 py-2">NORMAL</td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
<!-- Action Area -->
<div class="flex flex-col items-center justify-center text-center mt-12 gap-6 bg-primary p-12 rounded-2xl relative overflow-hidden">
<div class="absolute inset-0 bg-gradient-to-br from-primary to-primary-container opacity-50"></div>
<!-- Abstract visual element -->
<div class="absolute left-0 bottom-0 opacity-20">
<svg fill="none" height="200" viewBox="0 0 400 200" width="400" xmlns="http://www.w3.org/2000/svg">
<path d="M0 200L400 0V200H0Z" fill="white"></path>
</svg>
</div>
<div class="relative z-10 flex flex-col items-center w-full">
<h2 class="text-white text-3xl font-manrope font-bold mb-2">PRONTO PARA CALCULAR?</h2>
<p class="text-on-primary-container text-sm mb-8 max-w-lg">Clique abaixo para executar o motor de cálculo com base nos arquivos enviados.</p>
<a class="bg-white text-primary px-12 py-5 rounded-md font-manrope text-lg font-extrabold tracking-tight shadow-xl hover:shadow-2xl hover:-translate-y-1 transition-all flex items-center justify-center gap-3 w-fit mb-8" href="{{DATA:SCREEN:SCREEN_18}}">
                    Calcular Fretes
                    <span class="material-symbols-outlined">analytics</span>
</a>
<div class="flex items-center justify-center gap-8">
<div class="flex items-center gap-2">
<span class="material-symbols-outlined text-on-primary-container text-sm" style="font-variation-settings: &quot;FILL&quot; 1;">check_circle</span>
<span class="text-on-primary-container text-xs font-medium">Auto-validação ativa</span>
</div>
</div>
</div>
</div>
<!-- Footer Meta -->
<div class="mt-12 pt-8 border-t border-slate-200 flex justify-between items-center">
<p class="text-xs text-slate-400 font-body"><br></p>
<div class="flex gap-4">


</div>
</div>
</main>
</div>




</body></html>