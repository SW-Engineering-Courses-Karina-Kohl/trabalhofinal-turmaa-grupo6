<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.edu.ufrgs.model.Freight" %>
<%@ page import="br.edu.ufrgs.model.Priority" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>

<%
NumberFormat currency =
        NumberFormat.getCurrencyInstance(
                new Locale("pt", "BR"));
%>

    <!DOCTYPE html>
    <html class="light" lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <link
            href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&amp;family=Inter:wght@400;500;600&amp;display=swap"
            rel="stylesheet">
        <link
            href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap"
            rel="stylesheet">
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

            body {
                font-family: 'Inter', sans-serif;
            }

            h1,
            h2,
            h3,
            .font-headline {
                font-family: 'Manrope', sans-serif;
            }

            .glass-card {
                background: rgba(255, 255, 255, 0.7);
                backdrop-filter: blur(12px);
                border: 1px solid rgba(255, 255, 255, 0.3);
            }

            .ambient-shadow {
                box-shadow: 0 12px 32px -4px rgba(0, 17, 58, 0.06);
            }
        </style>
    </head>

    <body class="bg-surface text-on-surface">
        <!-- SideNavBar -->
        <aside
            class="hidden md:flex flex-col h-screen w-64 fixed left-0 top-0 bg-[#f1f4f6] dark:bg-slate-900 z-50 overflow-y-auto p-6">
            <div class="flex items-center gap-3 mb-10">
                <div class="w-10 h-10 bg-primary rounded-lg flex items-center justify-center text-white">
                    <span class="material-symbols-outlined">local_shipping</span>
                </div>
                <div>
                    <h2
                        class="text-xl font-extrabold text-[#00113a] dark:text-white uppercase tracking-widest leading-tight">
                        Atlas Frete</h2>
                    <p class="text-[10px] font-manrope tracking-widest uppercase font-semibold text-slate-500">
                        Plataforma de Gestão Logística</p>
                </div>
            </div>
            <nav class="space-y-2">
                <a class="flex items-center gap-3 px-4 py-3 text-slate-500 dark:text-slate-400 hover:bg-slate-200 dark:hover:bg-slate-800 font-manrope text-sm tracking-tight transition-colors duration-200 rounded-lg"
                    href="${pageContext.request.contextPath}/index.jsp">
                    <span class="material-symbols-outlined">upload_file</span>
                    Upload de Arquivos
                </a>
                <a class="flex items-center gap-3 px-4 py-3 bg-[#ffffff] dark:bg-slate-800 text-[#00113a] dark:text-blue-400 font-bold rounded-lg shadow-sm font-manrope text-sm tracking-tight transition-all scale-98 active:opacity-80"
                    href="${pageContext.request.contextPath}/results">
                    <span class="material-symbols-outlined">analytics</span>
                    Resumo da Exportação
                </a>
            </nav>
        </aside>
        <!-- Main Canvas -->
        <main class="pl-64 min-h-screen">
            <!-- TopNavBar Shell -->
            <header
                class="fixed top-0 right-0 left-64 h-16 bg-[#f7fafc]/80 dark:bg-slate-950/80 backdrop-blur-xl z-40 flex justify-between items-center px-8 w-full font-['Manrope'] font-semibold">
                <div class="flex items-center">
                    <span class="text-lg font-black text-[#00113a] dark:text-white">Atlas Frete</span>
                </div>
                <div class="flex items-center gap-4">
                    <button class="p-2 rounded-full hover:bg-slate-100/50 transition-colors">
                        <span class="material-symbols-outlined text-slate-500"
                            data-icon="notifications">notifications</span>
                    </button>
                    <button class="p-2 rounded-full hover:bg-slate-100/50 transition-colors">
                        <span class="material-symbols-outlined text-slate-500" data-icon="settings">settings</span>
                    </button>
                </div>
            </header>
            <!-- Content Body -->
            <div class="pt-24 pb-10 px-10 max-w-7xl mx-auto space-y-10">
                <!-- Consolidated Header -->
                <section class="flex flex-col md:flex-row justify-between items-end gap-6">
                    <div class="space-y-2">
                        <span class="label-md uppercase tracking-[0.1em] text-secondary font-bold text-xs">Processamento
                            Concluído</span>
                        <h1 class="text-5xl font-extrabold text-primary tracking-tight leading-none">Resumo da
                            Exportação</h1>
                        <p class="text-on-surface-variant text-lg max-w-xl">Seu arquivo foi gerado com sucesso.
                            Verifique os dados consolidados abaixo.</p>
                    </div>
                    <a href="${pageContext.request.contextPath}/index.jsp"
                        class="group flex items-center gap-2 px-6 py-3 bg-surface-container-high rounded-lg text-primary font-semibold hover:bg-surface-container-highest transition-all duration-300">
                        <span class="material-symbols-outlined" data-icon="arrow_back">arrow_back</span>
                        Voltar ao Início
                    </a>
                </section>
                <!-- Success Bento Grid -->
                <div class="grid grid-cols-12 gap-6">
                    <!-- Summary Card -->
                    <div
                        class="col-span-12 lg:col-span-4 bg-primary-container p-8 rounded-xl relative overflow-hidden flex flex-col justify-between min-h-[320px]">
                        <div class="relative z-10">
                            <div
                                class="w-12 h-12 bg-white/10 backdrop-blur-md rounded-lg flex items-center justify-center mb-6">
                                <span class="material-symbols-outlined text-white" data-icon="check_circle"
                                    style="font-variation-settings: 'FILL' 1;">check_circle</span>
                            </div>
                            <h3 class="text-white/70 font-label uppercase tracking-widest text-xs font-bold mb-2">Total
                                Processado</h3>
                            <div class="text-6xl font-black text-white tracking-tighter">124</div>
                            <p class="text-on-primary-container font-medium mt-1">pedidos processados</p>
                        </div>
                        <div class="relative z-10 mt-8">
                            <div class="flex items-center gap-4 text-white/80 text-sm">
                                <div class="flex flex-col">
                                    <span class="font-bold text-white">0.4s</span>
                                    <span class="text-[10px] uppercase opacity-60">Latência</span>
                                </div>
                                <div class="w-px h-6 bg-white/20"></div>
                                <div class="flex flex-col">
                                    <span class="font-bold text-white">100%</span>
                                    <span class="text-[10px] uppercase opacity-60">Precisão</span>
                                </div>
                            </div>
                        </div>
                        <!-- Abstract Background Shape -->
                        <div class="absolute -bottom-10 -right-10 w-48 h-48 bg-white/5 rounded-full blur-3xl"></div>
                    </div>
                    <!-- Download CTA Card -->
                    <div
                        class="col-span-12 lg:col-span-8 surface-container-lowest p-1 rounded-xl shadow-sm bg-gradient-to-br from-white to-slate-50 flex flex-col border border-slate-100">
                        <div class="p-8 flex-grow flex flex-col justify-center items-center text-center space-y-6">
                            <div
                                class="w-20 h-20 bg-secondary-fixed rounded-full flex items-center justify-center shadow-inner">
                                <span class="material-symbols-outlined text-secondary text-4xl"
                                    data-icon="description">description</span>
                            </div>
                            <div>
                                <h2 class="text-2xl font-bold text-primary">Pronto para Exportação</h2>
                                <p class="text-on-surface-variant">O documento <strong>relatorio_final_v1.xlsx</strong>
                                    foi consolidado e está pronto para o seu ledger.</p>
                            </div>
                            <button
                                class="w-full max-w-md bg-primary hover:bg-primary-container text-white px-8 py-5 rounded-lg font-bold text-lg flex items-center justify-center gap-3 shadow-lg shadow-primary/10 transition-all active:scale-[0.98]">
                                <span class="material-symbols-outlined" data-icon="download">download</span>
                                Download Arquivo Final (.xlsx)
                            </button>
                        </div>
                    </div>
                    <!-- Full Results Table -->
                    <div class="col-span-12 bg-white rounded-xl ambient-shadow overflow-hidden">
                        <div class="px-8 py-6 flex items-center justify-between border-b border-slate-50">
                            <div class="flex items-center gap-3">
                                <div class="w-1.5 h-6 bg-secondary rounded-full"></div>
                                <h4 class="font-bold text-blue-900 font-headline">Registro de Operações</h4>
                            </div>
                            <div class="text-xs text-slate-400 font-medium">
                                Exibindo 5 de 1,240 registros
                            </div>
                        </div>
                        <div class="overflow-x-auto">
                            <table class="w-full text-left border-collapse">
                                <thead>
                                    <tr class="bg-surface-container-low/50">
                                        <th class="px-8 py-4 text-[10px] uppercase tracking-widest font-bold text-slate-500"
                                            style="width: 25%;">ID Pedido</th>
                                        <th class="px-8 py-4 text-[10px] uppercase tracking-widest font-bold text-slate-500"
                                            style="width: 25%;">Valor Frete</th>
                                        <th class="px-8 py-4 text-[10px] uppercase tracking-widest font-bold text-slate-500"
                                            style="width: 25%;">
                                            <div class="flex items-center gap-1 cursor-pointer text-secondary justify-center">
                                                Data Estimada
                                                <span class="material-symbols-outlined text-sm"
                                                    data-icon="expand_more">expand_more</span>
                                            </div>
                                        </th>
                                        <th class="px-8 py-4 text-[10px] uppercase tracking-widest font-bold text-slate-500 text-center"
                                            style="width: 25%;">Status</th>
                                    </tr>
                                </thead>

                                

                                <%
                                List<Freight> freights =
                                    (List<Freight>) request.getAttribute("freights");
                                %>
                                <tbody class="divide-y-0">
                                    <% for (Freight freight : freights) { %>

                                        <%
                                        String cssClass;
                                        String label;

                                        if (Priority.URGENT.equals(
                                                freight.getPriority())) {

                                            cssClass =
                                                "inline-flex items-center px-3 py-1 rounded-full text-[10px] font-bold uppercase tracking-wider bg-tertiary-fixed text-on-tertiary-fixed";

                                            label = "Urgente";

                                        } else if (
                                                Priority.LONG_DISTANCE.equals(
                                                        freight.getPriority())) {

                                            cssClass =
                                                "inline-flex items-center px-3 py-1 rounded-full text-[10px] font-bold uppercase tracking-wider bg-secondary-container text-on-secondary-container";

                                            label = "Longa Distância";

                                        } else {

                                            cssClass =
                                                "inline-flex items-center px-3 py-1 rounded-full text-[10px] font-bold uppercase tracking-wider bg-secondary-fixed text-on-secondary-fixed-variant";

                                            label = "Normal";
                                        }
                                        %>

                                        <tr class="group hover:bg-surface-container-low/50 transition-colors">

                                            <td class="px-8 py-6 font-mono text-xs font-semibold text-slate-600">
                                                #ORD-<%= freight.getOrderId() %>
                                            </td>

                                            <td class="px-8 py-6 font-bold text-blue-900">
                                                <%= currency.format(freight.getFreightValue()) %>
                                            </td>

                                            <td class="px-8 py-6 text-slate-600 font-medium text-center">
                                                <%= freight.getEstimatedDate() %>
                                            </td>

                                            <td class="px-8 py-6 text-center">
                                                <span class="<%= cssClass %>">
                                                    <%= label %>
                                                </span>
                                            </td>

                                        </tr>

                                        <% } %>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- "Active Route" Glass Card (Floating Logic) -->
                <div class="fixed bottom-10 right-10 z-50">

                </div>
            </div>
        </main>


    </body>

    </html>